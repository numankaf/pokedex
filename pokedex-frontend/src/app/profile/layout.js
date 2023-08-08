import AppTopbar from "@/components/layout/AppTopbar";

export default function ProfileLayout({children}) {
    return (
        <>
            <AppTopbar></AppTopbar>
            {children}
        </>
    )
}
